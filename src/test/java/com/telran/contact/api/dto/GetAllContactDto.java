package com.telran.contact.api.dto;

import com.telran.contact.models.Contact;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString

public class GetAllContactDto {
	List<ContactDto> contacts;
}
