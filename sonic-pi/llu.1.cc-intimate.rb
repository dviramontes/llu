#  5|--f--C--c-----f--C--c----f--C--c----f--C--c------|-  *Riff A
#  4|-----------G-----------G----------G----------G---|-

#  5|--f--G--C--f--c--D----c-------------|- *Riff B
#  4|-------------------G----------------|- Repeat 8 Times

# 5|--f--A--G---C-G--f--c--f--D-----D--c----|- *Riff C
# 4|------------------------------G---------|- Repeat 32 Times

notes1 = (ring :f5, :Cs5, :c5, :Gs4)
notes2 = (ring :f5, :Gs5,:Cs5, :f5, :c5, :Ds5,:Gs4, :c5)
notes3 = (ring :f5, :As5, :Gs5, :Cs5, :Gs5, :f5, :c5, :f5, :Ds5, :Gs5, :c5)

use_bpm 125
use_synth :pulse

live_loop :beat do 
  with_fx(:reverb, reps: 3, delay: 1, decay: 1, distort: 4.4,
  phase: 0.25) do
    sample :drum_bass_hard, release: 1.25
  end
  sleep 1
end

# molodies

16.times do
  with_fx(:reverb, decay: 1) do
    with_fx(:echo, release: 2.2,  phase: 0.125, distort: 4.4) do
      play notes1.tick
    end
  end
  sleep 1
end

32.times do
  with_fx(:reverb, decay: 1) do
    with_fx(:echo, release: 2.2,  phase: 0.125, distort: 4.4) do
      play notes2.tick
    end
  end
  sleep 0.5
end

32.times do 
  with_fx(:reverb, decay: 1) do
    with_fx(:echo, release: 2.2,  phase: 0.125, distort: 4.4) do
      play notes3.tick
    end
  end
  sleep 0.35
end