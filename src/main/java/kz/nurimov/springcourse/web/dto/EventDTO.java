package kz.nurimov.springcourse.web.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import kz.nurimov.springcourse.web.models.Club;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;

    @NotEmpty(message = "Type should not be empty")
    private String type;

    @NotEmpty(message = "Photo Url should not be empty")
    private String photoUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
