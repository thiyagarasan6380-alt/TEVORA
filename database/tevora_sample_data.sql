USE tevora_db;

INSERT INTO skills (skill_name)
VALUES
    ('Java'),
    ('Python'),
    ('C'),
    ('React'),
    ('JavaScript'),
    ('Spring Boot'),
    ('MySQL'),
    ('HTML'),
    ('CSS'),
    ('Node.js')
ON DUPLICATE KEY UPDATE
    skill_name = VALUES(skill_name);
