# Reich Phase + other samples
# Steve Reich's Piano Phase
# See:  https://en.wikipedia.org/wiki/Piano_Phase

_one = "~/Desktop/llu/00.wav"
load_samples [_one]

use_synth :piano

notes0 = (ring :E4, :Fs4, :B4, :Cs5, :D5, :Fs4, :E4, :Cs5, :B4, :Fs4, :D5, :Cs5)
notes1 = (ring :E4, :Fs4, :B4, :Cs5, :D5, :Fs4, :E4, :Cs5, :B4, :Fs4, :D5, :Cs5)

# =begin

## 1
live_loop :slow do
  uncomment do
    # sample _one, rate: -1, amp: 0.02
    play notes0.tick, release: 2.6, amp: 1.0
  end
  sleep 1
end

## 2
live_loop :fast do
  comment do
    with_synth :beep do
      sample _one, rate: -1.51, start: 0.00, finish: 1, amp: 0.0
      play notes1.tick, release: 0.6, amp: 1.0
    end
  end
  sleep 0.5
end

## 3

live_loop :faster do
  comment do
    with_synth :piano do
      play notes1.tick, release: 1
    end
  end
  sleep 0.25
end

## 4
live_loop :fastestest do
  comment do
    with_synth :piano do
      play notes1.tick, release: 1
    end
  end
  sleep 0.125
end

#=end
