package org.gs;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "app")
public class CustomConfig {

  @ConfigProperty(name = "message")
  public String message;
}
