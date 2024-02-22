package kz.nurimov.springcourse.web.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import kz.nurimov.springcourse.web.models.Event;
import kz.nurimov.springcourse.web.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubDTO {
    private Long id;

    @NotEmpty(message = "Club title should not be empty")
    private String title;

    @NotEmpty(message = "Photo link should not be empty")
    private String photoUrl;

    @NotEmpty(message = "Content should not be empty")
    public String content;

    private UserEntity createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<EventDTO> events;
}
