package pl.detectivegame.payload;

import lombok.Data;

@Data
public class SaveDetectiveCaseResponse {

    String saveId;

    public SaveDetectiveCaseResponse(String saveId) {
        this.saveId = saveId;
    }


}
