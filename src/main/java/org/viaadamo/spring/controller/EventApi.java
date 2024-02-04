package org.viaadamo.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.viaadamo.spring.controller.util.EventFieldSort;
import org.viaadamo.spring.dto.PersonalDTO;
import org.viaadamo.spring.entity.Event;
import org.viaadamo.spring.entity.Host;
import org.viaadamo.spring.entity.util.PersonalType;
import org.viaadamo.spring.exception.ViaAdamoException;

import java.util.List;

@Tag(name ="Event API", description = "Spring application with spring-boot 3.x")

public interface EventApi {
    @Operation(
            summary = "Retrieve a list of events",
            description = "Get all the events that are created. The response is a list of Event object",
            tags = {"get", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    ResponseEntity<List<Event>> getEvents();

    @Operation(
            summary = "Retrieve a event",
            description = "Get the event with equal id. The response is a Event object",
            tags = {"get", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    ResponseEntity<Event> getById(@PathVariable("id") Long id);

    @Operation(
            summary = "Create a event",
            description = "Generate a new event in the application. The response is the Event object generated.",
            tags = {"post", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<Event> newEvent(@RequestBody Event event);

    @Operation(
            summary = "Update a event",
            description = "Update a event that exists in the application. The response is the Event object modified.",
            tags = {"put", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "304", description = "Event id is mandatory.", content = {@Content(schema = @Schema())}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<Event> update(@RequestBody Event event);

    @Operation(
            summary = "Delete a event",
            description = "Delete a event that exists in the application. The response is a String with confirmation.",
            tags = {"delete", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "404", description = "Event Id not found.", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<String> delete(@PathVariable("id") Long id);

    @Operation(
            summary = "Event with more fences.",
            description = "Get the event with more fences in the application. The response is a Event object.",
            tags = {"get", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<Event> getFirstTaskOrderDesc();

    @Operation(
            summary = "Retrieve a list of events",
            description = "Get all the events that are created in a host. The response is a list of Event object",
            tags = {"get", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<List<Event>> getByHostId(@PathVariable("host_id") Long id);
    @Operation(
            summary = "Retrieve a list of events",
            description = "Get all the events that are created in the paging values, in addition to the field order. The response is a list of Event object",
            tags = {"get", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<List<Event>> getEventPage(@RequestParam(defaultValue = "1") Integer pageNo,
                                                    @RequestParam(defaultValue = "3") Integer pageSize,
                                                    @RequestParam(required = false) EventFieldSort sortBy,
                                                    @RequestParam(defaultValue = "ASC") Sort.Direction orderBy);

    @Operation(
            summary = "Retrieve a list of events",
            description = "Get all the events that are created on a date between two values. The response is a list of Event object",
            tags = {"get", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = Event.class))}),
                    @ApiResponse(responseCode = "500", description = "Date Format Incorrect", content = {@Content(schema = @Schema())}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<List<Event>> getByDate(@PathVariable("start") String start,
                                                 @PathVariable("end") String end) throws ViaAdamoException;
    @Operation(
            summary = "Retrieve a modified list of personal by event.",
            description = "Get all the personal of a type at an event. The response is a list of PesonalDTO object",
            tags = {"get", "event" }
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Success", content = {@Content(schema = @Schema(implementation = PersonalDTO.class))}),
                    @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
            }
    )
    public ResponseEntity<List<PersonalDTO>> getEventPersonalByType(@PathVariable("idEvent") Long idEvent, @PathVariable("typePersonal") PersonalType typePersonal);

}
