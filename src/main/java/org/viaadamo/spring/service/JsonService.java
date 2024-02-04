package org.viaadamo.spring.service;

import com.google.gson.*;
import org.springframework.stereotype.Service;
import org.viaadamo.spring.controller.util.JSONField;
import org.viaadamo.spring.entity.Event;
import org.viaadamo.spring.entity.Host;
import org.viaadamo.spring.entity.Manager;
import org.viaadamo.spring.entity.Personal;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class JsonService {

    EventService eventService;
    HostService hostService;
    ManagerService managerService;
    PersonalService personalService;

    public JsonService(EventService eventService, HostService hostService, ManagerService managerService, PersonalService personalService) {
        this.eventService = eventService;
        this.hostService = hostService;
        this.managerService = managerService;
        this.personalService = personalService;
    }

    public String getPath(JSONField entity) {
        String entityName = entity.getField();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        /*
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString(), formatter);
            }
        }).setPrettyPrinting().create();
        */
        // He intentado esto y varias cosas más pero no consigo que la lista de eventos se pueda printear. Tengo un problema con el DateTime


        switch (entityName) {
            case "Event":
                List<Event> events = eventService.findAll();
                try {
                    String json = gson.toJson(events);
                    return Paths.get(Files.writeString(Path.of("events.json"), json).toUri()).toAbsolutePath().toString();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case "Host":
                List<Host> hosts = hostService.findAll();
                try {
                    String json = gson.toJson(hosts);
                    return Paths.get(Files.writeString(Path.of("hosts.json"), json).toUri()).toAbsolutePath().toString();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case "Manager":
                List<Manager> managers = managerService.findAll();
                try {
                    String json = gson.toJson(managers);
                    return Paths.get(Files.writeString(Path.of("managers.json"), json).toUri()).toAbsolutePath().toString();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case "Personal":
                List<Personal> personal = personalService.findAll();
                try {
                    String json = gson.toJson(personal);
                    return Paths.get(Files.writeString(Path.of("personal.json"), json).toUri()).toAbsolutePath().toString();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        return "Missing JSON file";
    }



}
