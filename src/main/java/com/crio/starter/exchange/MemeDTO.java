package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class MemeDTO {
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "URL is required")
    private String url;

    @NotBlank(message = "Caption is required")
    private String caption;
    
}
