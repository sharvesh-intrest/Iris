# Why the fuck is this not a multimodule project?

As you may know, forge uses different versions of gradle between 1.12.2 - latest. Simply put, its a major pain to get things working all on the same gradle version, and because of this, we have put the core project on it's own, along with all the integrations in subfolders... yes, but not as a multimodule project due to different gradle versions.