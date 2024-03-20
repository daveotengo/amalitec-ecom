package com.amalitec.amalitececom.request_response.graphql.input;

import com.amalitec.amalitececom.auth.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterInput {
  private String name;
  private String email;
  private String password;
  private Role role;
}
