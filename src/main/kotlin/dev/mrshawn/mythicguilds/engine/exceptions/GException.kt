package dev.mrshawn.mythicguilds.engine.exceptions

import dev.mrshawn.mythicguilds.utils.messages.system.MSG

abstract class GException(message: MSG): Exception(message.toString())
