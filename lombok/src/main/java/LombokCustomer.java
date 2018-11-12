import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LombokCustomer {
    private String firstName;
    private String lastName;
    private LombokAddress address;
}
