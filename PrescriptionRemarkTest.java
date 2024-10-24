import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PrescriptionRemarkTest {

    private Date validDate;

    @BeforeEach
    public void setup() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        validDate = sdf.parse("22/10/23");
    }

    @Test
    public void testAddRemark_ValidBoundaryRemarks() {
        Prescription prescription1 = new Prescription(1, "Kenta", "Ohashi", 
                                                      "2-2-1 Kasumigaseki, Chiyoda City, Tokyo 100-0013, Japan", 
                                                      -2.25f, 80f, -0.50f, validDate, "Dr. Morita");
        Prescription prescription2 = new Prescription(2, "Yumi", "Nakamura", 
                                                      "4-6-15 Ginza, Chuo City, Tokyo 104-0061, Japan", 
                                                      +2.75f, 70f, +1.50f, validDate, "Dr. Fujimoto");
        assertTrue(prescription1.addRemark("This prescription seems accurate and reliable.", "Client"));
        assertTrue(prescription2.addRemark("Optometrist verifies the final results here.", "Optometrist"));
    }

    @Test
    public void testAddRemark_InvalidTooShort() {
        Prescription prescription1 = new Prescription(3, "Kazuki", "Tanaka", 
                                                      "1-2-3 Roppongi, Minato City, Tokyo 106-0032, Japan", 
                                                      -1.50f, 60f, -1.25f, validDate, "Dr. Suzuki");
        Prescription prescription2 = new Prescription(4, "Mika", "Sato", 
                                                      "2-12-1 Asakusa, Taito City, Tokyo 111-0034, Japan", 
                                                      +0.50f, 90f, +0.75f, validDate, "Dr. Honda");
        assertFalse(prescription1.addRemark("Too short.", "Client"));
        assertFalse(prescription2.addRemark("Very bad.", "Optometrist"));
    }

    @Test
    public void testAddRemark_InvalidTooLong() {
        Prescription prescription1 = new Prescription(5, "Sho", "Yamada", 
                                                      "3-7-1 Marunouchi, Chiyoda City, Tokyo 100-0005, Japan", 
                                                      +0.50f, 90f, 0.00f, validDate, "Dr. Ogawa");
        String longRemark1 = "This is an excessively long remark that exceeds the twenty-word limit to test how the application handles such large remarks in the validation process.";
        
        Prescription prescription2 = new Prescription(6, "Mai", "Takashi", 
                                                      "5-2-1 Shinjuku, Shinjuku City, Tokyo 160-0022, Japan", 
                                                      -0.75f, 120f, -1.00f, validDate, "Dr. Sasaki");
        String longRemark2 = "The optometrist should not accept this remark because it exceeds the maximum number of words allowed by the system for remarks.";
        
        assertFalse(prescription1.addRemark(longRemark1, "Optometrist"));
        assertFalse(prescription2.addRemark(longRemark2, "Client"));
    }

    @Test
    public void testAddRemark_ExceedingMaxRemarks() {
        Prescription prescription = new Prescription(7, "Hinata", "Nishi", 
                                                     "1-1-1 Shinjuku, Shinjuku City, Tokyo 160-0022, Japan", 
                                                     -0.50f, 100f, -0.75f, validDate, "Dr. Hashimoto");
        prescription.addRemark("The first remark is valid and accepted.", "Client");
        prescription.addRemark("The second remark is valid and accepted.", "Optometrist");
        assertFalse(prescription.addRemark("This remark should not be added.", "Client"));
    }

    @Test
    public void testAddRemark_InvalidFirstCharacterLowercase() {
        Prescription prescription = new Prescription(8, "Rina", "Suzuki", 
                                                     "1-1-1 Nihonbashi, Chuo City, Tokyo 103-0027, Japan", 
                                                     +0.75f, 90f, +0.50f, validDate, "Dr. Yamamoto");
        assertFalse(prescription.addRemark("this remark starts with a lowercase letter.", "Client"));
    }

    @Test
    public void testAddRemark_InvalidRemarkType() {
        Prescription prescription = new Prescription(9, "Sora", "Kawasaki", 
                                                     "2-8-1 Akasaka, Minato City, Tokyo 107-0052, Japan", 
                                                     -1.00f, 120f, -1.50f, validDate, "Dr. Murakami");
        assertFalse(prescription.addRemark("This is a valid remark but wrong type.", "Customer"));
    }
}
