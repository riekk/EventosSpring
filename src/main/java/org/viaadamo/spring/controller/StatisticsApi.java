package org.viaadamo.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.viaadamo.spring.service.EventService;
import org.viaadamo.spring.service.HostService;

import java.time.LocalDate;

@Tag(name ="Statistics API", description = "Spring application with spring-boot 3.x")
public interface StatisticsApi {


    @Operation(
            summary = "Retrieve number of events in a host.",
            description = "Get the count of events in a host. The response is a long number.",
            tags = {"get", "statistics" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Long.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    ResponseEntity<Long> getTotalEventsById(@PathVariable("id") Long id);

    @Operation(
            summary = "Retrieve number of events in a period.",
            description = "Get the count of events between two dates. The response is a long number.",
            tags = {"get", "statistics" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Long.class))}),
                    @ApiResponse(responseCode = "400", description = "Date Format Incorrect", content = {@Content(schema = @Schema())}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<Long> countEventsDateBetween(@PathVariable("startDate") LocalDate startDate,
                                                       @PathVariable("endDate") LocalDate endDate);
}
