package ht11.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScoreDto {
    private int score;

    public ScoreDto(int score) {
        this.score = score;
    }
}
