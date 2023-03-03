# Using PMD

Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset. Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false negative). Explain why you would not solve this issue.

## Answer

A true positive is located in class LutherIntegratorTest at line 305. The problem detected is an empty method. This is not a bug in itself, but i would add a comment explaining why the body of the method is empty.

A false negative is detected in file FastSineTransformerTest at line 188. This is an empty catch block, but a comment says it is an expected behaviour.