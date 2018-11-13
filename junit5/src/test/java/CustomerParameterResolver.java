import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomerParameterResolver implements ParameterResolver {
    public static final String FAKER_URL_PATTERN = "http://faker.hook.io?property=%s&locale=en";
    public static final String FAKER_PROPERTY_FIRST_NAME = "name.firstName";
    public static final String FAKER_PROPERTY_LAST_NAME = "name.lastName";


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == Customer.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        int id = 42;
        return new Customer(id, getFirstName(), getLastName());
    }

    private String getFirstName() {
        return getFromFaker(FAKER_PROPERTY_FIRST_NAME);
    }

    private String getLastName() {
        return getFromFaker(FAKER_PROPERTY_LAST_NAME);
    }

    private String getFromFaker(String property) {
        try {
            URL obj = new URL(String.format(FAKER_URL_PATTERN, property));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            try (InputStream stream = con.getInputStream()) {
                JsonElement element = new JsonParser().parse(new InputStreamReader(stream));
                return element.getAsJsonPrimitive().getAsString();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

