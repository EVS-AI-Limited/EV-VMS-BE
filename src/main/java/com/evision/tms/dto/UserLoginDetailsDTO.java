package com.evision.tms.dto;

import lombok.*;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserLoginDetailsDTO {
private int userID;
private String userName;
private String password;
}
