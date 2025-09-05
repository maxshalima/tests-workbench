package by.delaidelo.tests.testworks.dto;

import jakarta.validation.constraints.NotNull;

public record SelectListItemDto(@NotNull Long id, String title) {}

