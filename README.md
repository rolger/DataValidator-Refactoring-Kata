# Data-Validator-Refactoring-Kata

// Vergleichbare andere Katas:
// Email Sender code refactroing kata
// error conditions refactroing kata?
// ValidateAndAddProduct-Refactoring-Kata

// Mögliche Änderungen:
// - neue Columns
// - Änderungen in der Reihenfolge der Columns
// - Änderungen/Fehler in Validierung
// - Mehrere Validierungen für ein Feld

// welches Design Möglichkeiten stehen zur Verfügung
//  Liste von commands
//  Liste von Specifications
//  Monade
//  Composite Pattern
//  Template Method
//  Vavr Validation Framework?

//v1
//List<ErrorInfo> errors = new ValidatorCollector()
//	.validate(t -> Validators::validFirstName, "first name must contain characters", columns.get(0))
//	.validate(t -> Validators::validLastName, "last name must contain characters", columns.get(1))
//	.validate(t -> Validators::validXYZ, "last name must contain characters", columns.get(4))
//	.errors();

//v2
//List<ValidationSpec> specs = List.of(
//	new ValidationSpec(t -> Validators::validFirstName, "first name must contain characters", columns.get(0))
//	new ValidationSpec(t -> Validators::validXYZ, "last name must contain characters", columns.get(4))
//);
//List<ErrorInfo> errors = specs.stream.flatMap(ValidationSpec::valdiate).collect(....);


// welche Wege stehen uns zur Verfügung
// bottom up refactoring
// top down
// mixed?
