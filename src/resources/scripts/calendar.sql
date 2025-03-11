
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
   calendar_id INT NOT NULL,
    day INT NOT NULL,
    month INT NOT NULL,
    year INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    time VARCHAR(10) NOT NULL,
    FOREIGN KEY (calendar_id) REFERENCES user(id)
);
