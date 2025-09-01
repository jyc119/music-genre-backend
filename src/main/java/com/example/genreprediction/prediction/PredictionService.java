package com.example.genreprediction.prediction;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PredictionService {
    private final WebClient mlClient;

    public PredictionResponse predict(MultipartFile file) throws Exception{
        if(file.isEmpty() || !file.getOriginalFilename().endsWith("wav")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please upload a .wav file");
        }

        ByteArrayResource resource = new ByteArrayResource(file.getBytes()){
            @Override public String getFilename() {return file.getOriginalFilename();}
        };


        MLPredictResponse ml = mlClient.post()
                .uri("/predict")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", resource))
                .retrieve()
                .bodyToMono(MLPredictResponse.class)
                .block();

        assert ml != null;
        return new PredictionResponse(ml.genre(), ml.confidence());
    }
}
