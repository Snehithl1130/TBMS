// ============================
// MODULE 3: Booking & Payment Processing
// ============================

// Entity: Booking
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Long userId;
    private Long packageId;
    private String startDate;
    private String endDate;
    private String status;
    private Long paymentId;

    // Getters and Setters
}

// Entity: Payment
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private Long userId;
    private Long bookingId;
    private double amount;
    private String status;
    private String paymentMethod;

    // Getters and Setters
}

// Repository: BookingRepository & PaymentRepository
public interface BookingRepository extends JpaRepository<Booking, Long> {}
public interface PaymentRepository extends JpaRepository<Payment, Long> {}

// Service: BookingService
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    public Booking createBooking(Booking booking) {
        return bookingRepo.save(booking);
    }

    public Payment processPayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    public List<Booking> getBookings() {
        return bookingRepo.findAll();
    }
}

// Controller: BookingController
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService service;

    @PostMapping("/create")
    public Booking book(@RequestBody Booking booking) {
        return service.createBooking(booking);
    }

    @PostMapping("/payment")
    public Payment pay(@RequestBody Payment payment) {
        return service.processPayment(payment);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return service.getBookings();
    }
}
