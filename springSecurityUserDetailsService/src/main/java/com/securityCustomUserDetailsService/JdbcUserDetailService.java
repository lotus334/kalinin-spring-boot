package com.securityCustomUserDetailsService;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.Optional;

public class JdbcUserDetailService extends MappingSqlQuery<UserDetails> implements UserDetailsService {

    public JdbcUserDetailService(DataSource ds) {
        super(ds, """
                select 
                t_user.c_username,
                t_user_password.c_password,
                array_agg(t_user_authority.c_authority) as c_authorities
                from t_user 
                left join t_user_password on t_user.id = t_user_password.id_user
                left join t_user_authority on t_user.id = t_user_authority.id_user
                where t_user.c_username = :username
                group by t_user.id, t_user_password.id
                """);
        this.declareParameter(new SqlParameter("username", Types.VARCHAR)); // именованный параметр
        this.compile(); // TODO чтобы сделать неизменяемым???
    }

    @Override
    protected UserDetails mapRow(final ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .username(rs.getString("c_username"))
                .password(rs.getString("c_password"))
                .authorities(extractAuthorities(rs))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return Optional.ofNullable(this.findObjectByNamedParam(Map.of("username", username)))
                .orElseThrow(() -> new UsernameNotFoundException("User $s not found".formatted(username)));
    }

    private String[] extractAuthorities(final ResultSet rs) throws SQLException {
        Object[] objects = (Object[]) rs.getArray("c_authorities").getArray();
        String[] result = new String[objects.length];

        for (int i = 0; i < objects.length; i++) {
            result[i] = (String) objects[i];
        }

        return result;
    }
}
