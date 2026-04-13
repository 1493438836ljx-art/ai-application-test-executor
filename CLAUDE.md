# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

AI Application Test Executor — a Spring Boot application for executing AI application tests. Built by Huawei Cloud Open Labs.

## Build & Run Commands

- **Build**: `./mvnw clean package`
- **Run**: `./mvnw spring-boot:run`
- **Run all tests**: `./mvnw test`
- **Run single test**: `./mvnw test -Dtest=TestClassName` or `./mvnw test -Dtest=TestClassName#methodName`
- **Skip tests**: `./mvnw clean package -DskipTests`

## Tech Stack

- Java 21
- Spring Boot 3.4.5 (with `spring-boot-starter-web`)
- Maven (wrapper included: `mvnw`/`mvnw.cmd`)

## Project Structure

- Base package: `com.huawei.cloudopenlabs.aiapplicationtestexecutor`
- Entry point: `AiApplicationTestExecutorApplication.java`
- Configuration: `src/main/resources/application.properties`

## Coding Rules

1. All log messages must be in English — no Chinese characters in any log output.
2. Cyclomatic complexity must not exceed 20 per method. Break up complex methods early.
3. Never use `System.out.print`, `System.out.println`, or `System.out.printf`. Use SLF4J (`log`) instead.
4. Every new Java class must include the following copyright header at the top of the file:
   ```java
   /*
   * Copyright (c) Huawei Technologies Co., Ltd. 2026-2026. All rights reserved.
   */
   ```
