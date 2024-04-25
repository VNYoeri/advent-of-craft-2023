package food;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Supplier;

public record Food(LocalDate expirationDate,
                   Boolean approvedForConsumption,
                   UUID inspectorId) {
    public boolean isEdible(Supplier<LocalDate> now) {
        return unexpiredAt(now) && inspectorApprovedConsumption();
    }

    private boolean unexpiredAt(Supplier<LocalDate> now) {
        return this.expirationDate.isAfter(now.get());
    }

    private boolean inspectorApprovedConsumption() {
        return this.inspectorId != null && this.approvedForConsumption;
    }
}