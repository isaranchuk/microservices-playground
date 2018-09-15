CREATE TABLE IF NOT EXISTS `phone` (
  `phone_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(24) NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT NOW(),
  `updated_at` TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW(),
  PRIMARY KEY (`phone_id`))
ENGINE = InnoDB;

INSERT INTO `phone` VALUES
  (1, "Iphone X", 900.99, "https://www.apple.com/iphone-xs/", "Super Retina in two sizes — including the largest display ever on an iPhone. Even faster Face ID. The smartest, most powerful chip in a smartphone. And a breakthrough dual-camera system. iPhone XS is everything you love about iPhone. Taken to the extreme.", NOW(), NOW()),
  (2, "Galaxy Note9", 899.99, "https://www.samsung.com/us/mobile/galaxy-note9/", "Galaxy Note9 puts powerful technology in the hands of pioneers who demand more. Innovative features and design make it the only phone to keep up with the next generation of achievers.", NOW(), NOW());
