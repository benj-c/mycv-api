package com.mycv.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActDeatUser {

    @NotNull(message = "userId cannot be empty")
    @Range(min = 1, max = Integer.MAX_VALUE, message = "User ID should be in range {min}-{max}")
    private Integer userId;

    @NotNull(message = "isEnabled cannot be empty")
    private Boolean isEnabled;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ActDeatUser{");
        sb.append("userId=").append(userId);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append('}');
        return sb.toString();
    }
}
