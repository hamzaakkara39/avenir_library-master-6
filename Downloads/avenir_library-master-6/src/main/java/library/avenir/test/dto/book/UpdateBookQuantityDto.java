package library.avenir.test.dto.book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateBookQuantityDto {
    @NotNull
    @Min(0)
    @Max(100)
    private Integer quantity;
}
