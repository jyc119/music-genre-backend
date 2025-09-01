package com.example.genreprediction.prediction;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/genres")

public class PredictionController {
    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {this.predictionService = predictionService;}

    @PostMapping(value="/predict", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PredictionResponse predict(@RequestPart("file")MultipartFile file) throws Exception{
        return predictionService.predict(file);
    }
}
