# Spark Fireworks

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Build Status](https://travis-ci.org/sparkfireworks/spark-fireworks.svg?branch=master)](https://travis-ci.org/sparkfireworks/spark-fireworks)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1b3ede6ca9e6431dbbc5561253b1ccb4)](https://www.codacy.com/app/mail_62/akka-goose-game?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ricardomiranda/akka-goose-game&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/1b3ede6ca9e6431dbbc5561253b1ccb4)](https://www.codacy.com/app/mail_62/akka-goose-game?utm_source=github.com&utm_medium=referral&utm_content=ricardomiranda/akka-goose-game&utm_campaign=Badge_Coverage)

Spark Fireworks works with Spark dataframes originated by parsing Json or XML files. Reading valid Json and XML files often generates complex dataframes that need to be exploded and flatenned in several columns to produce a totally expanded tree.

This project lets you consume any valid Json or XML and fully expand its tree into a tabular form.

It assumes a target Hive table is provided, so that the final results can be persisted. Avery simple Json configuration file is required.

Spark Fireworks is written in Scala, highly test covered.

## Authors
*   [Gonçalo Castro](https://github.comg/oncaloCCastro)
*   [Ricardo Miranda](https://github.com/ricardoMiranda)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
