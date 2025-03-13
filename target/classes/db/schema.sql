CREATE TABLE IF NOT EXISTS vulnerabilities(
    id INTEGER NOT NULL,
    category VARCHAR(255),
    name VARCHAR(255),
    description VARCHAR(255),
    cve VARCHAR(255),
    severity VARCHAR(255),
    locationFile VARCHAR(255),
    locationLineStart VARCHAR(255),
    locationLineEnd VARCHAR(255),

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS weaknesses(
    id INTEGER NOT NULL;
    vulnerabilityId INTEGER NOT NULL,
    type VARCHAR(255),
    name VARCHAR(255), 
    value VARCHAR(255),
    url VARCHAR(255),

    PRIMARY KEY(id),
    FOREIGN KEY(vulnerabilityId) REFERENCES vulnerabilities(id)
);