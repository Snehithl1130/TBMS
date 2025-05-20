// Project: Travel Package Booking System
// Backend: Java Spring Boot
// Database: MySQL

// ============================
// MODULE 1: User & Role Management
// ============================

// Entity: User
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String password;
    private String role;
    private String contactNumber;
    
    // Getters and Setters
}

// Repository: UserRepository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

// Service: UserService
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

// Controller: UserController
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}

// ============================
// MODULE 2: Travel Package Management
// ============================

// Entity: TravelPackage
@Entity
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    private String title;
    private String description;
    private int duration;
    private double price;
    private String includedServices;

    // Getters and Setters
}

// Repository: TravelPackageRepository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {}

// Service: TravelPackageService
@Service
public class TravelPackageService {
    @Autowired
    private TravelPackageRepository repo;

    public List<TravelPackage> getAllPackages() {
        return repo.findAll();
    }

    public TravelPackage addPackage(TravelPackage travelPackage) {
        return repo.save(travelPackage);
    }

    public void deletePackage(Long id) {
        repo.deleteById(id);
    }
}

// Controller: TravelPackageController
@RestController
@RequestMapping("/api/packages")
public class TravelPackageController {
    @Autowired
    private TravelPackageService service;

    @GetMapping
    public List<TravelPackage> getAllPackages() {
        return service.getAllPackages();
    }

    @PostMapping
    public TravelPackage addPackage(@RequestBody TravelPackage travelPackage) {
        return service.addPackage(travelPackage);
    }

    @DeleteMapping("/{id}")
    public void deletePackage(@PathVariable Long id) {
        service.deletePackage(id);
    }
}

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

// ============================
// MODULE 4: Reviews & Ratings
// ============================

// Entity: Review
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private Long userId;
    private Long packageId;
    private int rating;
    private String comment;
    private String timestamp;

    // Getters and Setters
}

// Repository: ReviewRepository
public interface ReviewRepository extends JpaRepository<Review, Long> {}

// Service: ReviewService
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repo;

    public Review addReview(Review review) {
        return repo.save(review);
    }

    public List<Review> getAllReviews() {
        return repo.findAll();
    }
}

// Controller: ReviewController
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping
    public Review submitReview(@RequestBody Review review) {
        return service.addReview(review);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return service.getAllReviews();
    }
}

// ============================
// MODULE 5: Travel Insurance & Assistance
// ============================

// Entity: Insurance
@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceId;

    private Long userId;
    private Long bookingId;
    private String coverageDetails;
    private String provider;
    private String status;

    // Getters and Setters
}

// Entity: AssistanceRequest
@Entity
public class AssistanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    private Long userId;
    private String issueDescription;
    private String status;
    private String resolutionTime;

    // Getters and Setters
}

// Repository: InsuranceRepository & AssistanceRepository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {}
public interface AssistanceRepository extends JpaRepository<AssistanceRequest, Long> {}

// Service: InsuranceService
@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepo;
    @Autowired
    private AssistanceRepository assistanceRepo;

    public Insurance addInsurance(Insurance insurance) {
        return insuranceRepo.save(insurance);
    }

    public AssistanceRequest requestHelp(AssistanceRequest request) {
        return assistanceRepo.save(request);
    }

    public List<AssistanceRequest> getRequests() {
        return assistanceRepo.findAll();
    }
}

// Controller: InsuranceController
@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService service;

    @PostMapping("/buy")
    public Insurance buyInsurance(@RequestBody Insurance insurance) {
        return service.addInsurance(insurance);
    }

    @PostMapping("/help")
    public AssistanceRequest help(@RequestBody AssistanceRequest request) {
        return service.requestHelp(request);
    }

    @GetMapping("/requests")
    public List<AssistanceRequest> getRequests() {
        return service.getRequests();
    }
}

// End of Full Backend Code Implementation
