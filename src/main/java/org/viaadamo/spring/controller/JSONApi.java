package org.viaadamo.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.viaadamo.spring.controller.util.JSONField;

@Tag(name ="JSON API", description = "Spring application with spring-boot 3.x")
public interface JSONApi {


    @Operation(
            summary = "Retrieve a file path.",
            description = "Generate a JSON file for entity selected. The response is a string with file path.",
            tags = {"get", "json" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = String.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    ResponseEntity<String> getEventPage(@RequestParam(name = "Entity") JSONField entity);


}