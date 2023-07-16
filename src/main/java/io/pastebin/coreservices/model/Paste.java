package io.pastebin.coreservices.model;

import io.pastebin.coreservices.utilities.Helper;
import io.pastebin.coreservices.utilities.TimeUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paste {
    @Id
    private String key;

    @Column(name = "data")
    private String data;

    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Column(name = "expiryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiryDate;

    @Transient
    private TimeUnit expiryTimeUnit;

    @Transient
    private int expiryTimeUnitValue;


    public Paste(String data) {
        this.data = data;
    }

    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    public void setExpiryDate() {
        int minutesToAdd = this.expiryTimeUnitValue * Helper.timeUnitEnumValues.get(this.expiryTimeUnit);
        this.expiryDate = this.getCreatedDate().plusMinutes(minutesToAdd);
    }
}
