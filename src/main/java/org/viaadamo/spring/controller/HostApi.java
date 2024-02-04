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
import org.viaadamo.spring.dto.HostDTO;
import org.viaadamo.spring.entity.Host;

import java.util.List;

@Tag(name ="Host API", description = "Spring application with spring-boot 3.x")
public interface HostApi {

    @Operation(
            summary = "Retrieve a list of hosts",
            description = "Get all the hosts that are created. The response is a list of Host object",
            tags = {"get", "host" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Host.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<List<Host>> getHosts();

    @Operation(
            summary = "Retrieve a list of host name and events count.",
            description = "Get all the hosts and count their events that are created. The response is a modified list of Host object. (HostDTO)",
            tags = {"get", "host" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = HostDTO.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<List<HostDTO>> getEventsByHost();

    @Operation(
            summary = "Retrieve a list of hosts",
            description = "Get all Hosts matching the Manager name . The response is a list of Host object",
            tags = {"get", "host" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Host.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<List<Host>> getByManagerName(@PathVariable("manager_name") String name);
}
