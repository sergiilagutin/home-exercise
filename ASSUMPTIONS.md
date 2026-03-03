Input data:
* every input element should be an integer number in scala Int range: [Int.MinValue, Int.MaxValue]. Decimals are not supported
* input triangle should have valid structure (every next row has one more element comparing to previous row). Otherwise, it's not a triangle. Validation for this case was added. 

Solver:
* only valid triangles should be passed to `Solver` methods. All required validations are performed in `Main` and `TriangleReader` classes

CLI:
* CLI interface support only min path functionality. It was done to reduce efforts to support both operations. Max path functionality is done in `Solver` and tested properly.