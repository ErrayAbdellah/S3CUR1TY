package com.spring.s3cur1ty.dto.response;

import com.spring.s3cur1ty.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String name ;
    private List<Authority> authorities;
}
