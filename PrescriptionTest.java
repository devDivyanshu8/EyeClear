import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class PrescriptionTest {

    private Date validDate;
    private Date anotherValidDate;

    @BeforeEach
    public void setup() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        validDate = sdf.parse("10/11/23");
        anotherValidDate = sdf.parse("18/05/24");
    }

    @Test
    public void testAddPrescription_ValidBoundaryNames() {
        Prescription prescription1 = new Prescription(1, "Yuki", "Sato", 
                                                      "4 Chome-2-8 Shibakoen, Minato City, Tokyo 105-0011, Japan", 
                                                      -3.75f, 120f, -1.00f, validDate, "Dr. Nakamura");
        Prescription prescription2 = new Prescription(2, "Yumi", "Takahashi", 
                                                      "2-3-10 Nishi-Shinjuku, Shinjuku City, Tokyo 160-0023, Japan", 
                                                      +1.25f, 100f, +3.50f, anotherValidDate, "Dr. Watanabe");
        assertTrue(prescription1.addPrescription());
        assertTrue(prescription2.addPrescription());
    }

    @Test
    public void testAddPrescription_InvalidFirstName_TooShort() {
        Prescription prescription1 = new Prescription(3, "Aki", "Suzuki", 
                                                      "1-2-3 Roppongi, Minato City, Tokyo 106-0032, Japan", 
                                                      -2.25f, 100f, -2.75f, validDate, "Dr. Sato");
        Prescription prescription2 = new Prescription(4, "Re", "Abe", 
                                                      "3-6-9 Nihonbashi, Chuo City, Tokyo 103-0027, Japan", 
                                                      +1.50f, 50f, -1.50f, validDate, "Dr. Kondo");
        assertFalse(prescription1.addPrescription());
        assertFalse(prescription2.addPrescription());
    }

    @Test
    public void testAddPrescription_InvalidLastName_TooLong() {
        Prescription prescription1 = new Prescription(5, "Hiroshi", "YamamotoExtraLongName", 
                                                      "5-15-5 Shibuya, Shibuya City, Tokyo 150-0002, Japan", 
                                                      -0.50f, 90f, 0.00f, validDate, "Dr. Kobayashi");
        Prescription prescription2 = new Prescription(6, "Mei", "Tanakahashiiiiiiiiiiiiiiiiii", 
                                                      "4-16-2 Ginza, Chuo City, Tokyo 104-0061, Japan", 
                                                      -1.75f, 120f, +0.75f, anotherValidDate, "Dr. Hayashi");
        assertFalse(prescription1.addPrescription());
        assertFalse(prescription2.addPrescription());
    }

    @Test
    public void testAddPrescription_InvalidAddress_TooShort() {
        Prescription prescription1 = new Prescription(7, "Kazumi", "Tanaka", 
                                                      "Incomplete Addr", 
                                                      -5.50f, 160f, -3.25f, anotherValidDate, "Dr. Kimura");
        Prescription prescription2 = new Prescription(8, "Haruka", "Ito", 
                                                      "Short Addr", 
                                                      +0.50f, 45f, +1.00f, validDate, "Dr. Nakamura");
        assertFalse(prescription1.addPrescription());
        assertFalse(prescription2.addPrescription());
    }

    @Test
    public void testAddPrescription_InvalidSphereValue() {
        Prescription prescription1 = new Prescription(9, "Haruka", "Ito", 
                                                      "2-16-1 Kamiyama-cho, Shibuya City, Tokyo 150-0047, Japan", 
                                                      -21.00f, 85f, -1.50f, validDate, "Dr. Matsumoto");
        Prescription prescription2 = new Prescription(10, "Hiroshi", "Watanabe", 
                                                      "1-2-3 Marunouchi, Chiyoda City, Tokyo 100-0005, Japan", 
                                                      +21.50f, 120f, -0.50f, anotherValidDate, "Dr. Sato");
        assertFalse(prescription1.addPrescription());
        assertFalse(prescription2.addPrescription());
    }

    @Test
    public void testAddPrescription_InvalidAxisValue() {
        Prescription prescription1 = new Prescription(11, "Shinji", "Nakamura", 
                                                      "3-1-1 Asakusa, Taito City, Tokyo 111-0032, Japan", 
                                                      +1.50f, 185f, -0.50f, anotherValidDate, "Dr. Watanabe");
        Prescription prescription2 = new Prescription(12, "Akira", "Kobayashi", 
                                                      "2-12-2 Nihonbashi, Chuo City, Tokyo 103-0027, Japan", 
                                                      -0.25f, -5f, +1.00f, validDate, "Dr. Takeda");
        assertFalse(prescription1.addPrescription());
        assertFalse(prescription2.addPrescription());
    }
}
