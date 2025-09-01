package com.example.genreprediction.prediction;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/health")
    public String health() {
        return "ok";
    }
}
