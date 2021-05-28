# Data-Validator-Refactoring-Kata

## Idee
Bevor Daten verarbeitet werden müssen sie oft validiert werden. Besonders an den Grenzen unserer Domain wollen überpüfen, ob die gelieferten Daten fachlich korrekt sind z.B. beim Import eines CSV Files.

Anfangs ist die Logik noch sehr einfach. Oft ändern sich die fachlichen Anforderungen oder die Daten verändern sich. Der Code verrottet bevor man sich versieht und plötzlich sind alle guten Clean Code Vorsätze egal. 

### Mögliche Änderungen
- neue Columns
- Änderungen in der Reihenfolge der Columns
- Änderungen/Fehler in Validierung
- Mehrere Validierungen für ein Feld

## Welche Wege stehen uns zur Verfügung
- bottom up refactoring
- top down
- mixed?

## Welches Design Möglichkeiten stehen zur Verfügung
-  Liste von commands
-  Liste von Specifications
-  Monade
-  Composite Pattern
-  Template Method
-  Vavr Validation Framework?

## Bonus Aufgaben

- Entferne die Primitve Obsession im API


```
// v1
List<ErrorInfo> errors = new ValidatorCollector()
	.validate(t -> Validators::validFirstName, "first name must contain characters", columns.get(0))
	.validate(t -> Validators::validLastName, "last name must contain characters", columns.get(1))
	.validate(t -> Validators::validXYZ, "last name must contain characters", columns.get(4))
	.errors();

// v2
List<ValidationSpec> specs = List.of(
	new ValidationSpec(t -> Validators::validFirstName, "first name must contain characters", columns.get(0))
	new ValidationSpec(t -> Validators::validXYZ, "last name must contain characters", columns.get(4))
);
List<ErrorInfo> errors = specs.stream.flatMap(ValidationSpec::valdiate).collect(....);

```

## Vergleichbare andere Katas
- Email Sender code refactroing kata
- error conditions refactroing kata?
- ValidateAndAddProduct-Refactoring-Kata

