package com.example.miau;

import com.example.miau.domain.Permission;
import com.example.miau.domain.Role;
import com.example.miau.domain.UserAccount;
import com.example.miau.service.PermissionService;
import com.example.miau.service.RoleService;
import com.example.miau.service.UserAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

@SpringBootApplication
public class MiauApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiauApplication.class, args);
    }

    @Bean
    CommandLineRunner logInitialData(UserAccountService userAccountService,
                                     RoleService roleService,
                                     PermissionService permissionService) {
        return args -> {
            System.out.println("\n=== Usuarios registrados ===");
            for (UserAccount user : userAccountService.getAllUsers()) {
                String roleNames = user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(", "));
                System.out.printf("- %s (%s) -> Roles: %s%n", user.getUsername(), user.getEmail(), roleNames);
            }

            System.out.println("\n=== Roles configurados ===");
            for (Role role : roleService.getAllRoles()) {
                String permissionNames = role.getPermissions().stream()
                        .map(Permission::getName)
                        .collect(Collectors.joining(", "));
                System.out.printf("- %s -> Permisos: %s%n", role.getName(), permissionNames);
            }

            System.out.println("\n=== Permisos disponibles ===");
            for (Permission permission : permissionService.getAllPermissions()) {
                System.out.printf("- %s%n", permission.getName());
            }
            System.out.println();
        };
    }
}
