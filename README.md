# DataValidator-Refactoring-Kata

![example workflow](https://github.com/Craftsmanship-Community-Nagarro/DataValidator-Refactoring-Kata/actions/workflows/java-build.yml/badge.svg)

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
-  Liste von Commands
-  Liste von Specifications
-  Monade
-  Composite Pattern
-  Template Method
-  Vavr Validation Framework?

## Bonus Aufgaben

- Finde und entferne die Primitve Obsession im API

## Vergleichbare andere Katas
- [Clarify-Exception-Refactoring-Kata](https://github.com/emilybache/Clarify-Exception-Refactoring-Kata)
- [ValidateAndAddProduct-Refactoring-Kata](https://github.com/emilybache/ValidateAndAddProduct-Refactoring-Kata)

