package com.telran.contact.api.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString

public class ContactDto {
	long id;
	String name;
	String lastname;
	String email;
	String phone;
	String address;
	String description;
}
