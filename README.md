# Spark Fireworks

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://travis-ci.org/sparkfireworks/spark-fireworks.svg?branch=master)](https://travis-ci.org/sparkfireworks/spark-fireworks)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a0d361f5f03e45b4ae1d97d67145deee)](https://www.codacy.com/app/mail_62/spark-fireworks?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sparkfireworks/spark-fireworks&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/a0d361f5f03e45b4ae1d97d67145deee)](https://www.codacy.com/app/mail_62/spark-fireworks?utm_source=github.com&utm_medium=referral&utm_content=sparkfireworks/spark-fireworks&utm_campaign=Badge_Coverage)

Spark Fireworks works with Spark dataframes originated by parsing Json or XML files. Reading valid Json and XML files often generates complex dataframes that need to be exploded and flatenned in several columns to produce a totally expanded tree.

This project lets you consume any valid Json or XML and fully expand its tree into a tabular form.

It assumes a target Hive table is provided, so that the final results can be persisted. Avery simple Json configuration file is required.

Spark Fireworks is written in Scala, highly test covered.

Builds are automated using Travis CI and code coverage and quality are checked with Codacy.

To run unit tests type:
```bash
sbt test
```

## Authors
*   [Gonçalo Castro](https://github.com/GoncaloCCastro)
*   [Ricardo Miranda](https://github.com/RicardoMiranda)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
