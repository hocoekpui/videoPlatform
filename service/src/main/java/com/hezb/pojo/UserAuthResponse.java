package com.hezb.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAuthResponse {

    private List<RoleAuthOperationInfo> roleAuthOperationList;

    private List<RoleMenuInfo> roleMenuList;
}
