package com.CustomerManagementMicroService.albanero.Domain;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateError {
    private int status;
    private String message;
}