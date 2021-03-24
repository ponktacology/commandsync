package me.ponktacology.commandsync.api.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

@Getter
public class ConfigurationFactory {

    private static final String PATH = System.getProperty("user.dir") + File.separator + "plugins" + File.separator;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private final String fileName;

    public ConfigurationFactory(String path) {
        this.fileName = path;
    }

    public void save(Configuration configuration) {
        File file = new File(PATH + fileName);

        try {
            if (!file.exists()) {

                new File(file.getParent()).mkdirs();
                file.createNewFile();
            }

            String json = GSON.toJson(configuration);

            System.out.println("SAVED " + file.toPath() + " " + json);
            Files.write(file.toPath(), Collections.singleton(json), StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T extends Configuration> T reload(Class<? extends Configuration> defaultConfiguration) {
        return load(defaultConfiguration);
    }

    public <T extends Configuration> T load(Class<? extends Configuration> defaultConfiguration) {
        File file = new File(PATH + fileName);

        if (!file.exists()) {
            try {
                new File(file.getParent()).mkdirs();
                file.createNewFile();

                Object instance = defaultConfiguration.newInstance();
                String json = GSON.toJson(instance);

                Files.write(file.toPath(), Collections.singleton(json), StandardCharsets.UTF_8, StandardOpenOption.WRITE);
                return (T) instance;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            return (T) GSON.fromJson(new FileReader(file), defaultConfiguration);
        } catch (Exception e) {
            return null;
        }
    }
}
