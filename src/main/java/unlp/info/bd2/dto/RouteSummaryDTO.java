package unlp.info.bd2.dto;

public record RouteSummaryDTO(
        String routeName,
        Long purchasesCount,
        Double averagePrice
) {}
