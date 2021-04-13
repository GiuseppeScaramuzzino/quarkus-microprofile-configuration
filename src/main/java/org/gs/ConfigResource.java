package org.gs;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/")
public class ConfigResource {

  @Inject
  @ConfigProperty(name = "config.message.inject", defaultValue = "Default message")
  String message;

  @Inject
  CustomConfig customConfig;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getMessageByInject() {
    return message;
  }

  @GET
  @Path("/provider")
  @Produces(MediaType.TEXT_PLAIN)
  public String getMessageByConfigProvider() {
    Config config = ConfigProvider.getConfig();
    String message = config.getValue("config.message.provider", String.class);
    return message;
  }

  @GET
  @Path("/providerOptional")
  @Produces(MediaType.TEXT_PLAIN)
  public String getMessageByConfigProviderOptional() {
    return ConfigProvider.getConfig()
        .getOptionalValue("config.message.provider", String.class)
        .orElse("");
  }

  @GET
  @Path("/custom")
  @Produces(MediaType.TEXT_PLAIN)
  public String getConfig() {
    return customConfig.message;
  }
}
