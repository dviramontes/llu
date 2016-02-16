# A|  5|--f--C--c-----f--C--c----f--C--c----f--C--c------|-  *Riff A
# B|  4|-----------G-----------G----------G----------G---|-

notes1 = (ring :f5, :Cs5, :c5, :Gs4)
notes2 = (ring :f5, :Cs5, :c5, :Gs4)

use_bpm 125
use_synth :pulse

live_loop :beat do
  with_fx(:reverb, reps: 2, delay: 1, decay: 1, distort: 2.4,
  phase: 0.25) do
    sample :drum_bass_hard, release: 1.25
  end
  sleep 1
end

live_loop :melody do
  with_fx(:reverb, decay: 3) do
    with_fx(:echo, release: 2.2,  phase: 0.125) do
      play notes1.tick
      sleep 1
    end
  end
end




