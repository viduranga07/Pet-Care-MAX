-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2026 at 10:44 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `petcare_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  `veterinarian_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` time NOT NULL,
  `reason` text DEFAULT NULL,
  `status` enum('Scheduled','Completed','Cancelled','No-show') NOT NULL DEFAULT 'Scheduled',
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `pet_id`, `veterinarian_id`, `service_id`, `appointment_date`, `appointment_time`, `reason`, `status`, `created_at`) VALUES
(1, 1, 1, 1, '2026-09-11', '09:00:00', 'Routine health consultation', 'Completed', '2026-09-11 10:16:34'),
(2, 2, 2, 2, '2026-09-11', '10:00:00', 'Annual vaccination', 'Scheduled', '2026-09-11 10:16:34'),
(3, 3, 3, 1, '2026-09-11', '11:00:00', 'Skin allergy examination', 'Scheduled', '2026-09-11 10:16:34'),
(4, 4, 1, 6, '2026-09-12', '14:00:00', 'Surgical consultation', 'Scheduled', '2026-09-11 10:16:34'),
(5, 5, 2, 3, '2026-09-13', '09:30:00', 'Regular grooming', 'Scheduled', '2026-09-11 10:16:34'),
(6, 6, 2, 1, '2026-09-13', '11:00:00', 'General health check', 'Scheduled', '2026-09-11 10:16:34');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `full_name`, `phone`, `email`, `address`, `created_at`) VALUES
(1, 'John Perera', '0712345678', 'john.perera@gmail.com', 'Colombo', '2026-09-11 10:16:34'),
(2, 'Sarah Fernando', '0771234567', 'sarah.fernando@gmail.com', 'Nugegoda', '2026-09-11 10:16:34'),
(3, 'Nimal Silva', '0759876543', 'nimal.silva@gmail.com', 'Kottawa', '2026-09-11 10:16:34'),
(4, 'Amanda Perera', '0764567890', 'amanda.perera@gmail.com', 'Maharagama', '2026-09-11 10:16:34'),
(5, 'Kasun Wijesinghe', '0723456789', 'kasun.w@gmail.com', 'Dehiwala', '2026-09-11 10:16:34');

-- --------------------------------------------------------

--
-- Table structure for table `medications`
--

CREATE TABLE `medications` (
  `medication_id` int(11) NOT NULL,
  `medication_name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `unit_price` decimal(10,2) NOT NULL DEFAULT 0.00,
  `stock_quantity` int(11) NOT NULL DEFAULT 0,
  `status` enum('Active','Inactive') NOT NULL DEFAULT 'Active',
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `medications`
--

INSERT INTO `medications` (`medication_id`, `medication_name`, `description`, `unit_price`, `stock_quantity`, `status`, `created_at`) VALUES
(1, 'Amoxicillin', 'Antibiotic medication', 450.00, 100, 'Active', '2026-09-11 10:16:34'),
(2, 'Cetirizine', 'Antihistamine medication', 120.00, 150, 'Active', '2026-09-11 10:16:34'),
(3, 'Meloxicam', 'Anti-inflammatory medication', 300.00, 80, 'Active', '2026-09-11 10:16:34'),
(4, 'Vitamin Supplement', 'General vitamin supplement', 250.00, 200, 'Active', '2026-09-11 10:16:34'),
(5, 'Deworming Tablet', 'Medication for parasite control', 180.00, 120, 'Active', '2026-09-11 10:16:34');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_method` enum('Cash','Card','Bank Transfer') NOT NULL,
  `payment_date` datetime NOT NULL DEFAULT current_timestamp(),
  `status` enum('Paid','Pending','Refunded') NOT NULL DEFAULT 'Paid'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `appointment_id`, `amount`, `payment_method`, `payment_date`, `status`) VALUES
(1, 1, 3500.00, 'Cash', '2026-09-11 10:16:34', 'Paid'),
(2, 2, 2500.00, 'Card', '2026-09-11 10:16:34', 'Paid'),
(3, 3, 3500.00, 'Card', '2026-09-11 10:16:34', 'Paid'),
(4, 4, 25000.00, 'Bank Transfer', '2026-09-11 10:16:34', 'Pending'),
(5, 5, 4500.00, 'Cash', '2026-09-11 10:16:34', 'Paid'),
(6, 6, 4000.00, 'Card', '2026-09-11 10:16:34', 'Paid'),
(7, 6, 1312.00, 'Cash', '2026-10-11 22:21:11', 'Paid');

-- --------------------------------------------------------

--
-- Table structure for table `pets`
--

CREATE TABLE `pets` (
  `pet_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `pet_name` varchar(100) NOT NULL,
  `species` varchar(50) NOT NULL,
  `breed` varchar(100) DEFAULT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pets`
--

INSERT INTO `pets` (`pet_id`, `customer_id`, `pet_name`, `species`, `breed`, `gender`, `date_of_birth`, `weight`, `notes`) VALUES
(1, 1, 'Bruno', 'Dog', 'Labrador Retriever', 'Male', '2021-05-12', 28.50, 'Friendly and active'),
(2, 1, 'Milo', 'Cat', 'Persian', 'Male', '2022-08-20', 4.80, 'Indoor cat'),
(3, 2, 'Luna', 'Dog', 'Golden Retriever', 'Female', '2020-03-15', 24.20, 'Allergic to certain foods'),
(4, 3, 'Rocky', 'Dog', 'German Shepherd', 'Male', '2019-11-05', 32.00, 'Regular health checks required'),
(5, 4, 'Coco', 'Cat', 'British Shorthair', 'Female', '2023-01-10', 4.20, 'Healthy'),
(6, 5, 'Max', 'Dog', 'Beagle', 'Male', '2022-06-18', 12.70, 'Very active');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `service_id` int(11) NOT NULL,
  `service_name` varchar(100) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `status` enum('Active','Inactive') NOT NULL DEFAULT 'Active',
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`service_id`, `service_name`, `description`, `price`, `status`, `created_at`) VALUES
(1, 'General Consultation', 'General veterinary consultation and health assessment', 3500.00, 'Active', '2026-09-11 10:16:34'),
(2, 'Vaccination', 'Routine vaccination service for pets', 2500.00, 'Active', '2026-09-11 10:16:34'),
(3, 'Pet Grooming', 'Professional pet grooming and cleaning service', 4500.00, 'Active', '2026-09-11 10:16:34'),
(4, 'Dental Care', 'Dental examination and cleaning', 6000.00, 'Active', '2026-09-11 10:16:34'),
(5, 'Laboratory Test', 'Blood and laboratory testing', 7500.00, 'Active', '2026-09-11 10:16:34'),
(6, 'Surgery', 'Veterinary surgical procedures', 25000.00, 'Active', '2026-09-11 10:16:34'),
(7, 'Health Check', 'Complete general health examination', 4000.00, 'Active', '2026-09-11 10:16:34');

-- --------------------------------------------------------

--
-- Table structure for table `treatments`
--

CREATE TABLE `treatments` (
  `treatment_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `diagnosis` text NOT NULL,
  `treatment_description` text NOT NULL,
  `treatment_date` date NOT NULL,
  `notes` text DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `treatments`
--

INSERT INTO `treatments` (`treatment_id`, `appointment_id`, `diagnosis`, `treatment_description`, `treatment_date`, `notes`, `created_at`) VALUES
(1, 1, 'Mild skin irritation', 'Topical treatment and antihistamine prescribed', '2026-09-11', 'Follow-up recommended after 7 days', '2026-09-11 10:16:34'),
(2, 3, 'Food-related skin allergy', 'Antihistamine treatment and dietary recommendation', '2026-09-11', 'Avoid suspected allergenic food', '2026-09-11 10:16:34');

-- --------------------------------------------------------

--
-- Table structure for table `treatment_medications`
--

CREATE TABLE `treatment_medications` (
  `treatment_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `dosage` varchar(100) NOT NULL,
  `frequency` varchar(100) NOT NULL,
  `duration` varchar(100) NOT NULL,
  `instructions` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `treatment_medications`
--

INSERT INTO `treatment_medications` (`treatment_id`, `medication_id`, `dosage`, `frequency`, `duration`, `instructions`) VALUES
(1, 2, '10 mg', 'Once daily', '7 days', 'Give after food'),
(2, 2, '10 mg', 'Once daily', '5 days', 'Give after food');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `role` enum('Admin','Receptionist','Veterinarian') NOT NULL,
  `status` enum('Active','Inactive') NOT NULL DEFAULT 'Active',
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password_hash`, `full_name`, `role`, `status`, `created_at`) VALUES
(1, 'admin', 'TGNuMkUt5zYGmw3IhnOz8A==:8PnwoIXt+wB3Lee6J1sKKWP7jHdd0qJJlmnrAWldmAw=', 'System Administrator', 'Admin', 'Active', '2026-09-11 10:16:34'),
(2, 'reception', 'vS4SI8/0XU/atSMLXa8N/Q==:gHfpUxAUyRPl9ueAa00Jr3JvVMReMiHaxCz1d6d13Fg=', 'Nimali Perera', 'Receptionist', 'Active', '2026-09-11 10:16:34'),
(3, 'doctor', 'W9P2aCO2jm5vf8TqqVzscw==:Snbt6qvLpI2su8gHPX1+VrMHIT8MO2cnmB4KyLL9QKI=', 'Dr. Kasun Silva', 'Veterinarian', 'Active', '2026-09-11 10:16:34'),
(5, 'bimsara', '2sZbUF3COzPNN14THCX45Q==:YNSts9+69XSBJNZJ0df4knH5xeBNvP2Ff1x9ak86y4s=', 'Bimsara Hettiarachchi', 'Admin', 'Active', '2026-10-03 20:55:27');

-- --------------------------------------------------------

--
-- Table structure for table `veterinarians`
--

CREATE TABLE `veterinarians` (
  `veterinarian_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `specialization` varchar(100) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `availability` varchar(255) DEFAULT NULL,
  `status` enum('Active','Inactive') NOT NULL DEFAULT 'Active',
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `veterinarians`
--

INSERT INTO `veterinarians` (`veterinarian_id`, `full_name`, `specialization`, `phone`, `email`, `availability`, `status`, `created_at`) VALUES
(1, 'Dr. Kasun Silva', 'Veterinary Surgery', '0711111111', 'kasun.silva@petcare.lk', 'Monday-Friday 08:00-17:00', 'Active', '2026-09-11 10:16:34'),
(2, 'Dr. Nadeesha Perera', 'General Veterinary Medicine', '0722222222', 'nadeesha.perera@petcare.lk', 'Monday-Saturday 09:00-18:00', 'Active', '2026-09-11 10:16:34'),
(3, 'Dr. Chamara Fernando', 'Dermatology', '0733333333', 'chamara.fernando@petcare.lk', 'Tuesday-Saturday 10:00-18:00', 'Active', '2026-09-11 10:16:34');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `fk_appointment_pet` (`pet_id`),
  ADD KEY `fk_appointment_veterinarian` (`veterinarian_id`),
  ADD KEY `fk_appointment_service` (`service_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `medications`
--
ALTER TABLE `medications`
  ADD PRIMARY KEY (`medication_id`),
  ADD UNIQUE KEY `medication_name` (`medication_name`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `fk_payment_appointment` (`appointment_id`);

--
-- Indexes for table `pets`
--
ALTER TABLE `pets`
  ADD PRIMARY KEY (`pet_id`),
  ADD KEY `fk_pet_customer` (`customer_id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`service_id`),
  ADD UNIQUE KEY `service_name` (`service_name`);

--
-- Indexes for table `treatments`
--
ALTER TABLE `treatments`
  ADD PRIMARY KEY (`treatment_id`),
  ADD KEY `fk_treatment_appointment` (`appointment_id`);

--
-- Indexes for table `treatment_medications`
--
ALTER TABLE `treatment_medications`
  ADD PRIMARY KEY (`treatment_id`,`medication_id`),
  ADD KEY `fk_tm_medication` (`medication_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `veterinarians`
--
ALTER TABLE `veterinarians`
  ADD PRIMARY KEY (`veterinarian_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `medications`
--
ALTER TABLE `medications`
  MODIFY `medication_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `pets`
--
ALTER TABLE `pets`
  MODIFY `pet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `treatments`
--
ALTER TABLE `treatments`
  MODIFY `treatment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `veterinarians`
--
ALTER TABLE `veterinarians`
  MODIFY `veterinarian_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `fk_appointment_pet` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`pet_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_appointment_service` FOREIGN KEY (`service_id`) REFERENCES `services` (`service_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_appointment_veterinarian` FOREIGN KEY (`veterinarian_id`) REFERENCES `veterinarians` (`veterinarian_id`) ON UPDATE CASCADE;

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `fk_payment_appointment` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`) ON UPDATE CASCADE;

--
-- Constraints for table `pets`
--
ALTER TABLE `pets`
  ADD CONSTRAINT `fk_pet_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON UPDATE CASCADE;

--
-- Constraints for table `treatments`
--
ALTER TABLE `treatments`
  ADD CONSTRAINT `fk_treatment_appointment` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`) ON UPDATE CASCADE;

--
-- Constraints for table `treatment_medications`
--
ALTER TABLE `treatment_medications`
  ADD CONSTRAINT `fk_tm_medication` FOREIGN KEY (`medication_id`) REFERENCES `medications` (`medication_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tm_treatment` FOREIGN KEY (`treatment_id`) REFERENCES `treatments` (`treatment_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
