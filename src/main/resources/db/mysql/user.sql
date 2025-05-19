CREATE DATABASE IF NOT EXISTS bookingService;

ALTER DATABASE bookingService
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON bookingService.* TO 'bookingService'@'%' IDENTIFIED BY 'bookingService';
